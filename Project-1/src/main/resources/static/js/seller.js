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
        window.open('http://localhost:8088/seller/popup','_blank','width=800px,height=800px,toolbars=no,scrollbars=no');
    }

    static loadFile(input) {
        let file = input.files[0];

        let name = document.getElementById('fileName');
        name.textContent = file.name;

        let newImg = document.createElement('img');
        newImg.setAttribute('class', 'img')

        newImg.src = URL.createObjectURL(file);

        newImg.style.width = '70%';
        newImg.style.height = '70%';
        newImg.style.visibility = 'hidden';
        newImg.style.objectFit = 'contain';

        let container = document.getElementById('image-show');
        container.appendChild(newImg);
    }
}