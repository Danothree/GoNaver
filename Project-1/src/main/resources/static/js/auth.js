class Auth {

    constructor() {
    }

    static showEmailPopup () {
        new Common().confirmSeller(() => {
            alert("hhhhhh");
        })
    };

}
