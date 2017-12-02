var appName;
var popupMask;
var popupDialog;
var clientId;
var realm;
var redirect_uri;
var clientSecret;
var scopeSeparator;
var additionalQueryStringParams;
var username;
var password;

function handleLogin() {
    var scopes = [];

    var auths = window.swaggerUi.api.authSchemes || window.swaggerUi.api.securityDefinitions;
    if (auths) {
        var key;
        var defs = auths;
        for (key in defs) {
            var auth = defs[key];
            if (auth.type === 'oauth2' && auth.scopes) {
                var scope;
                if (Array.isArray(auth.scopes)) {
                    // 1.2 support
                    var i;
                    for (i = 0; i < auth.scopes.length; i++) {
                        scopes.push(auth.scopes[i]);
                    }
                }
                else {
                    // 2.0 support
                    for (scope in auth.scopes) {
                        scopes.push({scope: scope, description: auth.scopes[scope], OAuthSchemeKey: key});
                    }
                }
            }
        }
    }

    if (window.swaggerUi.api
        && window.swaggerUi.api.info) {
        appName = window.swaggerUi.api.info.title;
    }

    $('.api-popup-dialog').remove();
    popupDialog = $(
        [
            '<div class="api-popup-dialog">',
            '<div class="api-popup-title">Set Username and Password</div> <br />',
            '<div class="api-popup-content">',
            '<input type="text" placeholder="Username" id="username"> <br/> <br />',
            '<input type="text" placeholder="Password" id="password"> <br/>',
            '<p class="error-msg"></p>',
            '<div class="api-popup-actions"><button class="api-popup-authbtn api-button green" type="button">Authorize</button><button class="api-popup-cancel api-button gray" type="button">Cancel</button></div>',
            '</div>',
            '</div>'].join(''));
    $(document.body).append(popupDialog);

    var $win = $(window),
        dw = $win.width(),
        dh = $win.height(),
        st = $win.scrollTop(),
        dlgWd = popupDialog.outerWidth(),
        dlgHt = popupDialog.outerHeight(),
        top = (dh - dlgHt) / 2 + st,
        left = (dw - dlgWd) / 2;

    popupDialog.css({
        top: (top < 0 ? 0 : top) + 'px',
        left: (left < 0 ? 0 : left) + 'px'
    });

    popupDialog.find('button.api-popup-cancel').click(function () {
        popupMask.hide();
        popupDialog.hide();
        popupDialog.empty();
        popupDialog = [];
    });

    popupDialog.find('#password').keyup(function(event){
        if(event.keyCode == 13){
            $('button.api-popup-authbtn').click();
        }
    });
    $('button.api-popup-authbtn').unbind();
    popupDialog.find('button.api-popup-authbtn').click(function () {
        popupMask.hide();
        popupDialog.hide();

        username = popupDialog.find('#username')[0].value;
        password = popupDialog.find('#password')[0].value;
        var authSchemes = window.swaggerUi.api.authSchemes;
        var host = window.location;
        var pathname = location.pathname.substring(0, location.pathname.lastIndexOf("/"));
        var defaultRedirectUrl = host.protocol + '//' + host.host + pathname + '/o2c.html';
        var redirectUrl = window.oAuthRedirectUrl || defaultRedirectUrl;
        var url = null;
        var scopes = []
        var OAuthSchemeKeys = [];
        var state;

        //TODO: merge not replace if scheme is different from any existing
        //(needs to be aware of schemes to do so correctly)
        window.enabledScopes = scopes;

        for (var key in authSchemes) {
            if (authSchemes.hasOwnProperty(key)) { //only look at keys that match this scope.
                sendOauth2(authSchemes[key])
            }
        }
    });

    popupMask.show();
    popupDialog.show();
    return;
}


function handleLogout() {
    for (key in window.swaggerUi.api.clientAuthorizations.authz) {
        window.swaggerUi.api.clientAuthorizations.remove(key)
    }
    window.enabledScopes = null;
    $('.api-ic.ic-on').addClass('ic-off');
    $('.api-ic.ic-on').removeClass('ic-on');

    // set the info box
    $('.api-ic.ic-warning').addClass('ic-error');
    $('.api-ic.ic-warning').removeClass('ic-warning');
}

function initOAuth(opts) {
    var o = (opts || {});
    var errors = [];

    appName = (o.appName || errors.push('missing appName'));
    popupMask = (o.popupMask || $('#api-common-mask'));
    popupDialog = (o.popupDialog || $('.api-popup-dialog'));
    clientId = (o.clientId || errors.push('missing client id'));
    clientSecret = (o.clientSecret || null);
    realm = (o.realm || errors.push('missing realm'));
    scopeSeparator = (o.scopeSeparator || ' ');
    additionalQueryStringParams = (o.additionalQueryStringParams || {});

    if (errors.length > 0) {
        log('auth unable initialize oauth: ' + errors);
        return;
    }

    $('pre code').each(function (i, e) {
        hljs.highlightBlock(e)
    });
    $('.api-ic').unbind();
    $('.api-ic').click(function (s) {
        if ($(s.target).hasClass('ic-off'))
            handleLogin();
        else {
            handleLogout();
        }
        false;
    });
}

function clientCredentialsFlow(scopes, tokenUrl, OAuthSchemeKey) {
    var params = {
        'client_id': clientId,
        'client_secret': clientSecret,
        'scope': scopes.join(' '),
        'grant_type': 'client_credentials'
    }
    $.ajax(
        {
            url: tokenUrl,
            type: "POST",
            data: params,
            success: function (data, textStatus, jqXHR) {
                onOAuthComplete(data, OAuthSchemeKey);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                onOAuthComplete("");
            }
        });

}

function sendOauth2(auth) {
    var params = {
        'client_id': clientId,
        'scope': 'write',
        'grant_type': 'password',
        'username': username,
        'password': password
    };
    var url = auth.authorizationUrl;
    url += "?client_id=" + clientId;
    url += "&scope=" + params['scope'];
    url += "&grant_type=" + params['grant_type'];
    url += "&username=" + params['username'];
    url += "&password=" + params['password'];
    $.ajax(
        {
            url: url,
            type: "POST",
            headers: {
                "Accept": "application/json",
                "Authorization": 'Basic ' + btoa(clientId + ":" + clientSecret)
            },
            success: function (data, textStatus, jqXHR) {
                onOAuthComplete(data, auth);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                onOAuthComplete("");
            }
        });
}

window.processOAuthCode = function processOAuthCode(data) {
    var OAuthSchemeKey = data.state;
    var params = {
        'client_id': clientId,
        'code': data.code,
        'grant_type': 'authorization_code',
        'redirect_uri': redirect_uri
    };

    if (clientSecret) {
        params.client_secret = clientSecret;
    }

    $.ajax(
        {
            url: window.swaggerUi.tokenUrl,
            type: "POST",
            data: params,
            success: function (data, textStatus, jqXHR) {
                onOAuthComplete(data, OAuthSchemeKey);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                onOAuthComplete("");
            }
        });
};

window.onOAuthComplete = function onOAuthComplete(token, OAuthSchemeKey) {
    if (token) {
        if (token.error) {
            var checkbox = $('input[type=checkbox],.secured')
            checkbox.each(function (pos) {
                checkbox[pos].checked = false;
            });
        }
        else {
            var b = token.access_token;
            if (!OAuthSchemeKey) {
                OAuthSchemeKey = token.state;
            }
            if (b) {
                // if all roles are satisfied
                var o = null;
                $.each($('.auth .api-ic .api_information_panel'), function (k, v) {
                    var children = v;
                    if (children && children.childNodes) {
                        var requiredScopes = [];
                        $.each((children.childNodes), function (k1, v1) {
                            var inner = v1.innerHTML;
                            if (inner)
                                requiredScopes.push(inner);
                        });
                        var diff = [];
                        for (var i = 0; i < requiredScopes.length; i++) {
                            var s = requiredScopes[i];
                            //diff.push(s);
                            //if (window.enabledScopes && window.enabledScopes.indexOf(s) == -1) {
                            //}
                        }
                        if (diff.length > 0) {
                            o = v.parentNode.parentNode;
                            $(o.parentNode).find('.api-ic.ic-on').addClass('ic-off');
                            $(o.parentNode).find('.api-ic.ic-on').removeClass('ic-on');

                            // sorry, not all scopes are satisfied
                            $(o).find('.api-ic').addClass('ic-warning');
                            $(o).find('.api-ic').removeClass('ic-error');
                        }
                        else {
                            o = v.parentNode.parentNode;
                            $(o.parentNode).find('.api-ic.ic-off').addClass('ic-on');
                            $(o.parentNode).find('.api-ic.ic-off').removeClass('ic-off');

                            // all scopes are satisfied
                            $(o).find('.api-ic').addClass('ic-info');
                            $(o).find('.api-ic').removeClass('ic-warning');
                            $(o).find('.api-ic').removeClass('ic-error');
                        }
                    }
                });
                window.swaggerUi.api.clientAuthorizations.add('oauth2', new SwaggerClient.ApiKeyAuthorization('Authorization', 'Bearer ' + b, 'header'));

                console.log("Authentication Success")
            }
        }
    }
};
