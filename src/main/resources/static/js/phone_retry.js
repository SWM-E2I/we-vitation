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

        if (window.confirm('인증 번호를 다시 보내줄까?')) {
            $.ajax({
                type: 'POST',
                url: '/v1/web/phone/reissue',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('인증 번호를 다시 보냈어! 문자를 확인해줘');
            }).fail(function () {
                alert('인증 번호를 다시 보내는데 실패했어.. 잠시 후에 다시 시도해줘!');
            })
        }
     }
}

phone_retry.init();