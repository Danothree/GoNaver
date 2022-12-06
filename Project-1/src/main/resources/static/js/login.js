function memberUpdate() {
    const formData = {
        email : document.getElementById('email').value,
        password : document.getElementById('password').value,
        phone : document.getElementById('phone').value,
        city : document.getElementById('city').value,
        street : document.getElementById('street').value,
        postalCode : document.getElementById('postalCode').value
    };
    const check = common.nullCheck(formData,'email', 'password', 'phone', 'city', 'street', 'postalCode');
    if(check) {
        common.showAlert('정보를 다 입력해주세요');
        return false;
    };

    const options = {
        method : 'PATCH',
        headers : {
            'Content-Type': 'application/json'
        },
        body : JSON.stringify(formData),
        redirect : 'follow'
    };
    fetch('/members',options)
        .then((res) => {
            if(res.redirected) {
                alert('수정 완료!');
                window.location.href=res.url;
            }
        });

}

function memberDelete(email) {
    let data = new FormData();
    data.append('email', email);
    const options = {
        method : 'DELETE',
        body : data,
        redirect : 'follow'
    };
    if(confirm("정말로 삭제하시겠습니까?")) {
        fetch('/members',options)
            .then((res) => {
                if(res.redirected) {
                    common.showAlert('삭제 완료!');
                    window.location.href='/logout';
                }
            });
    }
}

function pwCheck() {
    let pw1 = document.querySelector('#password').value;
    let pw2 = document.querySelector('#passwordCheck').value;
    if(pw1 != pw2) {
        common.showAlert('비밀번호를 확인해주세요.');
        return false;
    } else {
        return true;
    }
}