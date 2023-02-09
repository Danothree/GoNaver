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

        const fetch = new CustomFetch("/seller", 'apply');
        fetch.post(emailInfo, {}).then(result => alert(result));
        this.showInputEmailCode();

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
                    alert("하하하하")
                }
            })
    }

}
