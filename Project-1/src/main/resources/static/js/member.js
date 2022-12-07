const member = {

    //회원 수정
    update : async () => {
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
    },

    //회원 탈퇴
    delete : (email) => {
        common.confirm('정말로 탈퇴하시겠습니까?', async (email) => {
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
        });
    },

    //회원가입 시 비밀번호 확인 Check
    pwCheck : () => {
        const pw1 = document.querySelector('#password').value;
        const pw2 = document.querySelector('#passwordCheck').value;
        if(pw1 != pw2) {
            common.showAlert('비밀번호를 확인해주세요.');
            return false;
        } else {
            return true;
        }
    },

    //로그인 시 null Check
    loginCheck : () => {
        const formData = {
            email : document.getElementById('email').value,
            password : document.getElementById('password').value
        };
        const check = common.nullCheck(formData,'email', 'password');
        if(check) {
            common.showAlert('정보를 다 입력해주세요');
            return false;
        }
    },

    //우편 검색
    getPostCode : () => {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                document.getElementById('address').value = "(" + data.zonecode+ ") " + roadAddr + data.jibunAddress;

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
}

