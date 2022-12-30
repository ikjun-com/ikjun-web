let index = {
    init: function() {
        $("#register").on("click", () => {
            this.register()
        })
    },

    register: function() {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
            nickname: $("#nickname").val()
        }

        $.ajax({
            type: "POST",
            url: "/ikjun/user/register",
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            dataType: "json"
        }).done(function(response) {
            alert("회원가입 완료")
            location.href = "/ikjun"
        }).fail(function(error) {
            alert(JSON.stringify(error))
        })

    }
}

index.init()