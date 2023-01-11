class Seller {
    static author(email) {
        new Common().confirm('판매자 등록을 하시겠습니까?', () => {
            const fetch = new CustomFetch('/seller','');
            let data = fetch.post(email);
        });
    }

    static del() {
        new Common().confirm('판매자 권한을 취소하시겠습니까?', () => {
            const email = document.getElementById('emailChk').value;
            const fetch = new CustomFetch('/seller','');
            let data = fetch.delete(email);
        });
    }

    static openItemDetail() {
        window.open('http://localhost:8088/seller/popup','_blank','width=150px,height=150px,toolbars=no,scrollbars=no');
    }
}