const seller = {
    author : (email) => {
        common.confirm('판매자 등록을 하시겠습니까?', () => {
            customFetch.asyncPost('/seller','', email);
        });
    },

    del : () => {
        common.confirm('판매자 권한을 취소하시겠습니까?', () => {
            const email = document.getElementById('emailChk').value;
            customFetch.delete('/seller',email);
        })
    }
}