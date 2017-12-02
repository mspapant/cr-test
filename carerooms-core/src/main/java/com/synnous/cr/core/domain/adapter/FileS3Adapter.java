package com.synnous.cr.core.domain.adapter;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.retry.RetryPolicy;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.synnous.cr.core.property.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.MessageFormat;

/**
 * The file adapter.
 *
 * @author : Manos Papantonakos on 28/3/2015.
 */
@Service
public class FileS3Adapter implements FileAdapter {

    /** The region. */
    private static final Region REGION = Region.getRegion(Regions.EU_WEST_2);

    /** Th URL pattern. */
    private static final String URL_PATTERN = "https://s3-" + REGION.getName() + ".amazonaws.com/{0}/{1}";

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public void deleteFile(final String bucket, final String filename) {
        AWSCredentials credentials = new ProfileCredentialsProvider(storageProperties.getAwsProfile()).getCredentials();
        AmazonS3 s3 = new AmazonS3Client(credentials);
        s3.setRegion(REGION);

        s3.deleteObject(bucket, filename);
    }

    @Override
    public String uploadFile(final String bucket, final String filename, final byte[] data, final String contentType) {
        final ClientConfiguration conf = new ClientConfiguration();
        conf.setSocketTimeout(120000);
        conf.setConnectionTimeout(10000);
        conf.setMaxErrorRetry(3);
        conf.setRetryPolicy(new RetryPolicy(new RetryPolicy.RetryCondition() {
            @Override
            public boolean shouldRetry(final AmazonWebServiceRequest amazonWebServiceRequest, final AmazonClientException e, final int retriesAttempted) {
                System.out.println("AMAZON Retry");
                return retriesAttempted < 2;
            }
        }, new RetryPolicy.BackoffStrategy() {
            @Override
            public long delayBeforeNextRetry(final AmazonWebServiceRequest amazonWebServiceRequest, final AmazonClientException e, final int retriesAttempted) {
                return 2000;
            }
        }, 2, true));

        final AWSCredentials credentials = new ProfileCredentialsProvider(storageProperties.getAwsProfile()).getCredentials();
        final AmazonS3Client s3 = new AmazonS3Client(credentials, conf);
        s3.setRegion(REGION);

        final InputStream stream = new ByteArrayInputStream(data);
        final ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(data.length);
        if (contentType != null) {
            meta.setContentType(contentType);
        }
        s3.putObject(new PutObjectRequest(bucket, filename, stream, meta).withCannedAcl(CannedAccessControlList.PublicRead));
        return MessageFormat.format(URL_PATTERN, bucket, filename);
    }
}
