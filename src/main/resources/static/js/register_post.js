let register_post = {
  init: function () {
    let _this = this;
    $('#register-next').on('click', function () {
      _this.register();
    });
  },
  register: function () {
    const ei = $("input[name=EI]:checked").val();
    const sn = $("input[name=SN]:checked").val();
    const ft = $("input[name=FT]:checked").val();
    const pj = $("input[name=PJ]:checked").val();
    const NOTHING_CHECKED = $("#dont-know").is(':checked');

    if ((ei === undefined || sn === undefined ||
        ft === undefined || pj === undefined)
        && NOTHING_CHECKED === false) {

      alert("MBTI의 각 자리를 전부 선택해줘!");
      return;
    }

    const intro = $('#introduction').val();
    let mbti = ei + sn + ft + pj;

    if (NOTHING_CHECKED === true) {
      mbti = $("#dont-know").val();
    }

    if (intro.length > 100) {
      alert("자기 소개는 100자 이내로 가능해!");
      return;
    }

    let data = {
      mbti: mbti,
      introduction: intro
    };

    $.ajax({
      type: 'POST',
      url: '/v1/web/register/additional',
      contentType: 'application/json; charset=utf-8',
      data: JSON.stringify(data)
    }).done(function () {
      alert('너의 정보를 저장했어!');
      window.location.href = '/v1/web/email';
    }).fail(function () {
      alert('너의 정보를 저장하는 데 실패했어.. 잠시 후 다시 시도해줘!');
    })

  }
}

register_post.init();