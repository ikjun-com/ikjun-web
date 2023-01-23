let start = {
    init: function() {
        $("#edit").on("click", () => {
            this.edit()
        })
    },

    edit: function() {
        let boardId = $('#boardId').val()
        let data = {
            title : $('#title').val(),
            content : $('#content').val(),
            answer : $('#answer').val(),
            explanation : $('#explanation').val(),
            major : $('#major').val(),
            subject : $('#subject').val()
        }

        $.ajax({
            type: "PUT",
            url: "/ikjun/board/" + boardId,
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            dataType: "json"
        }).done(function(response) {
            alert("수정완료")
            location.href = "/ikjun/board/" + boardId
        }).fail(function(error) {
            alert(JSON.stringify(error))
        })
    }
}

start.init()