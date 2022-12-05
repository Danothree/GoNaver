function memberUpdate() {
    const options = {
        method : "patch",
        headers : {
            "Content-Type":"application/json"
        },
        data : FormData
    };
    fetch('/members',options)
        .then(() => {alert("수정완료")});
}