function readURL(input) {
  if (input.files && input.files[0]) {
    let reader = new FileReader();
    reader.onload = function (e) {
      document.getElementById('preview').src = e.target.result;
    };
    reader.readAsDataURL(input.files[0]);
    $('#preview').css("display", "flex");
    $('#image-input-box').css("display", "none").children().css("display", "none");
  } else {
    $('#preview').src = '';
  }
}

function fileInput() {
  $('#chooseFile').click();
}