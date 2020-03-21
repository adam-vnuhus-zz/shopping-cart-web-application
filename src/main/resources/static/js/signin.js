$(document).ready(function() {
    $("#btn-signin").on("click",function (){
        if($('#signin-email').val().trim() === '' || $('#signin-password').val().trim() === '') {
            new PNotify({
                title: 'Error!',
                text: 'Nhập đầy đủ các thứ vào đi bạn êi -_-',
                type: 'error',
                delay: 500
            });
            return;
        }

        var data = {
            email: $('#signin-email').val().trim(),
            password: $('#signin-password').val().trim()
        }

        axios.post("/users/signin", data).then(function(res){
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
});