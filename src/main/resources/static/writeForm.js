let start = {
    init: function() {
        $("#write").on("click", () => {
            this.write()
        })
    },

    write: function() {
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
            answer: $("#answer").val(),
            explanation: $("#explanation").val(),
            major: $("#major").val(),
            subject: $("#subject").val(),
            unlockStar: $("#unlockStar").val()
        }

        $.ajax({
            type: "POST",
            url: "/ikjun/board/write",
            data: JSON.stringify(data),
            contentType: "application/json;charset=utf-8",
            dataType: "json"
        }).done(function(response) {
            alert("글쓰기 완료")
            location.href = "/ikjun"
        }).fail(function(error) {
            alert(JSON.stringify(error))
        })
    }
}

start.init()