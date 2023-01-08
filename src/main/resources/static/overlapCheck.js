let usernameOverlap = {
    init: function() {
        $("#usernameCheck").on("click", () => {
            this.usernameCheck()
        })
    },

    usernameCheck: function() {
        $.ajax({
            url: "/ikjun/loginForm/usernameCheck",
            type: "POST",
            dataType: "JSON",
            data: {"username": $("#username").val()}
        }).done(function(response) {
            if(response == true) {
                alert("아이디 중복")
            } else if(response == false) {
                $("usernameCheck").attr("value", "Y")
                alert("사용가능한 아이디")
            }
        })
    },
}

let emailOverlap = {
    init: function() {
        $("emailCheck").on("click", () => {
            this.emailCheck()
        })
    },

    emailCheck: function() {
        $.ajax({
            url: "/ikjun/loginForm/emailCheck",
            type: "POST",
            dataType: "JSON",
            data: {"email": $("#email").val()}
        }).done(function(response) {
            if(response == true) {
                alert("이메일 중복")
            } else if(response == false) {
                $("emailCheck").attr("value", "Y")
                alert("사용가능한 이메일")
            }
        })
    }
}

let nicknameOverlap = {
    init: function() {
        $("nicknameCheck").on("click", () => {
            this.nicknameCheck()
        })
    },

    nicknameCheck: function() {
        $.ajax({
            url: "/ikjun/loginForm/nicknameCheck",
            type: "POST",
            dataType: "JSON",
            data: {"nickname": $("#nickname").val()}
        }).done(function(response) {
            if(response == true) {
                alert("닉네임 중복")
            } else if(response == false) {
                $("nicknameCheck").attr("value", "Y")
                alert("사용가능한 닉네임")
            }
        })
    }
}

usernameOverlap.init()
emailOverlap.init()
nicknameOverlap.init()