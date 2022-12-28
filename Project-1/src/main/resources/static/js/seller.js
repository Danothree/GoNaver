const seller = {
    author : () => {
        common.confirm('판매자 등록을 하시겠습니까?', () => {
            const email = document.getElementById('emailChk').value;
            customFetch.post('/seller','', email);
        });
    }
}