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
        window.open('http://localhost:8088/items/popup','_blank','width=1000px,height=800px,toolbars=no,scrollbars=no');
    }

    static loadFile() {
        const previews = document.querySelector('#imageBox');

        const fileDOM = document.querySelector('#itemImgFile');

        fileDOM.addEventListener('change', () => {
            const imageSrc = URL.createObjectURL(fileDOM.files[0]);
            previews.src = imageSrc;
            // URL.revokeObjectURL(imageSrc);
        });
    }

    static imageCheck() {
        const fileDOM = document.querySelector('#itemImgFile');
        if(fileDOM.value === "") {
            new Common().showAlert('상품 이미지는 필수입니다.','warning');
            return false;
        }
        return true;
    }

    static formDataTrans(){
        let check = this.imageCheck();

        if(check) {
            document.getElementById('sellForm').submit();

            setTimeout(() => {
                opener.location.reload();
                self.close();
            },100);
        }
    }
}

