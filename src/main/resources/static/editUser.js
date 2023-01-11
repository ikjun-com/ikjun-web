let editUser = {
    init: function() {
        $('#editNickname').on("click", () => {
            this.edit()
        })
    },

    edit: function() {
        let data = {
            nickname : $('#nickname').val()
        }

        $.ajax({
            type: "PUT",
            url: "/ikjun/user/myprofile",
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            dataType: "json"
        }).done(function(response) {
            alert("변경 성공")
        }).fail(function(error) {
            alert(JSON.stringify(error))
        })
    }
}

editUser.init()