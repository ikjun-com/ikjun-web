let start = {
    init: function() {
        $("#usernameCheck").on("click", () => {
            this.usernameCheck()
        }),
        $("emailCheck").on("click", () => {
            this.emailCheck()
        }),
        $("nicknameCheck").on("click", () => {
            this.nicknameCheck()
        })
    },

    usernameCheck: function() {
        $.ajax({
            url: "/ikjun/loginForm/usernameCheck",
            type: "POST",
            dataType: "JSON",
            data: {"username": $("#username").val()},
            success: function(result) {
                if(result == true) {
                    alert("아이디 중복")
                } else if(result == false) {
                    $("usernameCheck").attr("value", "Y")
                    alert("사용가능한 아이디")
                }
            }
        })
    },

    emailCheck: function() {
        $.ajax({
            url: "/ikjun/loginForm/emailCheck",
            type: "POST",
            dataType: "JSON",
            data: {"email": $("#email").val()},
            success: function(result) {
                if(result == true) {
                    alert("이메일 중복")
                } else if(result == false) {
                    $("emailCheck").attr("value", "Y")
                    alert("사용가능한 이메일")
                }
            }
        })
    },

    nicknameCheck: function() {
        $.ajax({
            url: "/ikjun/loginForm/nicknameCheck",
            type: "POST",
            dataType: "JSON",
            success: function(result) {
                if(result == true) {
                    alert("닉네임 중복")
                } else if(result == false) {
                    $("nicknameCheck").attr("value", "Y")
                    alert("사용가능한 닉네임")
                }
            }
        })
    }
}

start.init()