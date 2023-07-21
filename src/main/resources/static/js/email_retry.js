let email_retry = {
    init : function () {
        let _this = this;
        $('#btn-email-retry').on('click', function () {
            _this.email_retry();
        });
    },
     email_retry : function () {
        let data = {
            emailName : $('#emailName').val(),
            emailDomain : $('#emailDomains').val()
        };

        if (window.confirm('인증번호를 재전송하시겠습니까?')) {
            $.ajax({
                type: 'POST',
                url: '/v1/web/email/reissue',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('인증 번호를 다시 보냈어! 이메일을 확인해줘');
            }).fail(function () {
                alert('인증 번호를 다시 보내는데 실패했어.. 잠시 후에 다시 시도해줘!');
            })
        }
     }
}

email_retry.init();