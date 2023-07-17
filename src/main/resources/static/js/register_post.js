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

    if (ei === undefined || sn === undefined ||
        ft === undefined || pj === undefined) {
      alert("MBTI를 전부 선택해주세요");
      return;
    }

    const intro = $('#introduction').val();
    const mbti = ei + sn + ft + pj;

    if (intro.length > 100) {
      alert("자기 소개는 100자 이내로 가능합니다.");
      return;
    }

    let data = {
      mbti: mbti,
      hobby: $('#hobby').val(),
      introduction: intro
    };

    $.ajax({
      type: 'POST',
      url: '/v1/web/register/additional',
      contentType: 'application/json; charset=utf-8',
      data: JSON.stringify(data)
    }).done(function () {
      alert('회원 정보가 저장되었습니다.');
      window.location.href = '/v1/web/email';
    }).fail(function () {
      alert('회원 정보 저장에 실패했습니다. 잠시 후 다시 시도해주세요.');
    })

  }
}

register_post.init();