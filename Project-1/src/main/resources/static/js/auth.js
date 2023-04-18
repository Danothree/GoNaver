class Auth {

    constructor() {
    }

    static showEmailPopup () {
        const sellerOption = {
            title: 'Best Ecommerce 판매자 신청',
            input: 'email',
            inputLabel: '이메일 인증',
            inputPlaceholder: 'Enter your email address'
        }
        swal.fire(sellerOption)
                .then(result => {
                    if(result.isConfirmed){
                        this.sendVerificationCode(result.value);
                    }
        });
    };

    static sendVerificationCode(email){
        const emailInfo = {
            "email" : email
        }

        const fetch = new CustomFetch("/seller", 'apply', false);
        fetch.post(emailInfo).then(this.showInputEmailCode());


    }

    static userCertified(emailCode) {
        const emailInfo = {
            "emailCode" : emailCode
        }
        const fetch = new CustomFetch("/seller", 'apply/certify',false);
        fetch.post(emailInfo, '').then(value => {
            if (value === emailCode) {
               new Common().showAlert("인증 완료")
            } else {
               new Common().showAlert("인증 실패",'error');
            }
        })
    }

    static showInputEmailCode() {
        const inputEmailCode = {
            title: 'Best Ecommerce 판매자 신청',
            input: 'text',
            inputLabel : '인증코드를 입력하세요.',
            showCancelButton : true
        }
        swal.fire(inputEmailCode)
            .then(result => {
                if(result.isConfirmed) {
                    this.userCertified(result.value);
                }
            })
    }

}
