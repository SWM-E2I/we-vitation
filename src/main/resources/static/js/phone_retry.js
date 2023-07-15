let phone_retry = {
    init : function () {
        let _this = this;
        $('#btn-phone-retry').on('click', function () {
            _this.phone_retry();
        });
    },
     phone_retry : function () {
        let data = {
            phone : $('#phone').val()
        };

        if (window.confirm('인증번호를 재전송하시겠습니까?')) {
            $.ajax({
                type: 'POST',
                url: '/v1/web/api/phone',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('인증번호가 재전송되었습니다.');
            }).fail(function () {
                alert('인증 정보 재전송에 실패했습니다.');
            })
        }
     }
}

phone_retry.init();