$(document).ready(function() {

    $("#btn-signup").on("click", function () {
        if ($('#signup-name').val().trim() === '' || $('#signup-email').val().trim() === '' || $('#signup-password').val().trim() === '' || $('#signup-password-confirm').val().trim() === '') {
            new PNotify({
                title: 'Error!',
                text: 'Nhập đầy đủ các thứ vào đi bạn êi -_-',
                type: 'error',
                delay: 500
            });
            return;
        }

        if (!isEmail($('#signup-email').val().trim())) {
            new PNotify({
                title: 'Error!',
                text: 'Chưa đúng định dạng email rồi bạn ơi -_-',
                type: 'error',
                delay: 500
            });
            return;
        }

        if ($('#signup-password').val().trim() !== $('#signup-password-confirm').val().trim()) {
            new PNotify({
                title: 'Error!',
                text: 'Password không trung nhau rồi bạn ơi -_-',
                type: 'error',
                delay: 500
            });
            return;
        }

        var data = {
            name: $('#signup-name').val().trim(),
            email: $('#signup-email').val().trim(),
            password: $('#signup-password').val().trim()
        }

        axios.post("/users/signup", data).then(function(res){
            if(res.data.success) {
                new PNotify({
                    title: 'Success!',
                    text: res.data.message,
                    type: 'success',
                    delay: 500
                });
                setTimeout(() => location.replace("/"), 500);
            } else  {
                new PNotify({
                    title: 'Error!',
                    text: res.data.message,
                    type: 'error',
                    delay: 500
                });
            }
        });
    });

    function isEmail(email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }
});