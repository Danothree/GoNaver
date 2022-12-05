function memberUpdate() {
    const formData = {
        email : document.getElementById('email').value,
        password : document.getElementById('password').value,
        phone : document.getElementById('phone').value,
        city : document.getElementById('city').value,
        street : document.getElementById('street').value,
        postalCode : document.getElementById('postalCode').value
    }

    const options = {
        method : 'PATCH',
        headers : {
            'Content-Type': 'application/json'
        },
        body : JSON.stringify(formData),
        redirect : 'follow'
    };
    fetch('/members',options)
        .then(() => alert('수정완료!'))
        .then(() => window.location.replace='http://localhost:8088/');
}

function memberDelete(email) {
    let data = {
        email : email
    };
    const options = {
        method : 'DELETE',
        headers : {
            'Content-Type': 'application/json'
        },
        body : JSON.stringify(data),
        redirect : 'follow'
    };
    if(confirm("정말로 삭제하시겠습니까?")) {
        fetch('/members',options)
            .then(() => alert('삭제완료!'))
            .then(() => window.location.replace='http://localhost:8088/');
    }
}