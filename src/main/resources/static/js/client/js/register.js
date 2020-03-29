$(document).ready(function() {

    $("#btn-register").on("click", function () {
        if ($('#register-name').val().trim() === '' || $('#register-email').val().trim() === '' || $('#register-password').val().trim() === ''
            || $('#register-password-confirmation').val().trim() === ''
            || $('#register-address').val().trim() === '' || $('#register-phone').val().trim() === '') {
            swal(
                'Error',
                'You should fill all values!',
                'error'
            );
            return ;
        }

        if (!isPhone($('#register-phone').val().trim())) {
            swal(
                'Error',
                'Not correct phone format!',
                'error'
            );
            return ;
        }

        if (!isEmail($('#register-email').val().trim())) {
            swal(
                'Error',
                'Not correct mail format!',
                'error'
            );
            return ;
        }

        if ($('#register-password').val().trim() !== $('#register-password-confirmation').val().trim()) {
            swal(
                'Error',
                'Password not same!',
                'error'
            );
            return ;
        }

        var data = {
            full_name: $('#register-name').val().trim(),
            email: $('#register-email').val().trim(),
            phone: $('#register-phone').val().trim(),
            password: $('#register-password').val().trim(),
            address: $('#register-address').val().trim(),
        }

        axios.post("/api/user", data).then(function(res){
            if(res.data.success) {
                swal(
                    'Done!',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.replace("/login");
                });
            } else  {
                swal(
                    'Error',
                    res.data.message,
                    'error'
                );
            }
        });
    });

    function isEmail(email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }

    function isPhone(phone) {
        var regex = /(09|01[2|6|8|9])+([0-9]{8})\b/;
        return regex.test(phone);
    }
});