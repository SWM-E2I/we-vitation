$(document).ready(function () {

  $("#dont-know").on("click", function () {
    // Uncheck
    $("#mbti-i").prop("checked", false);
    $("#mbti-e").prop("checked", false);
    $("#mbti-s").prop("checked", false);
    $("#mbti-n").prop("checked", false);

    $("#mbti-t").prop("checked", false);
    $("#mbti-f").prop("checked", false);
    $("#mbti-j").prop("checked", false);
    $("#mbti-p").prop("checked", false);
  })

  $(".mbti-box").on("click", function () {
    $("#dont-know").prop("checked", false);
  })
})