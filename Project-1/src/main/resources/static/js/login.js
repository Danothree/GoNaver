async function memberUpdate() {
    const formData = {
        email : document.getElementById('email').value,
        password : document.getElementById('password').value,
        passwordCheck : document.getElementById('passwordCheck').value,
        phone : document.getElementById('phone').value,
        city : document.getElementById('city').value,
        street : document.getElementById('street').value,
        postalCode : document.getElementById('postalCode').value
    };
    const check = common.nullCheck(formData,'email', 'password', 'passwordCheck', 'phone', 'city', 'street', 'postalCode');
    if(check) {
        common.showAlert('정보를 다 입력해주세요');
        return false;
    };

    if(!pwCheck()) {
        common.showAlert('비밀번호가 틀립니다.');
        return false;
    }

    const options = {
        method : 'PATCH',
        headers : {
            'Content-Type': 'application/json'
        },
        body : JSON.stringify(formData),
        redirect : 'follow'
    };
    const res = await fetch('/members',options);
    if(res.redirected) {
        common.showAlert('수정 완료!');
        window.location.href=res.url;
    }
}
function delConfirm(email) {
    common.confirm('정말로 탈퇴하시겠습니까?',memberDelete(email));
}
async function memberDelete(email) {
    let data = new FormData();
    data.append('email', email);
    const options = {
        method : 'DELETE',
        body : data,
        redirect : 'follow'
    };
    const res = await fetch('/members',options);
    if(res.redirected) {
        common.showAlert('탈퇴 완료!');
        window.location.href='/logout';
    };
}

function pwCheck() {
    let pw1 = document.querySelector('#password').value;
    let pw2 = document.querySelector('#password2').value;
    if(pw1 != pw2) {
        common.showAlert('비밀번호를 확인해주세요.');
        return false;
    } else {
        return true;
    }
}

function loginCheck() {
    const formData = {
        email : document.getElementById('email').value,
        password : document.getElementById('password').value
    };
    const check = common.nullCheck(formData,'email', 'password');
    if(check) {
        common.showAlert('정보를 다 입력해주세요');
        return false;
    };
}