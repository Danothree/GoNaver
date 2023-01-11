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
        const fetch = new CustomFetch("/seller/apply", email);
        const promise = fetch.get();
    }

}
