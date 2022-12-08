const common = {
    nullCheck : (data,...list) => {
        let result = false;
        for (let i = 0; i < list.length; i++) {
            if(data[list[i]] == undefined || data[list[i]] == "") {
                result = true;
            }
        }
        return result;
    },
    showAlert : (title, option) => {
        const defaultOption = {
            title : title,
            text : '',
            icon : 'info',
            confirmButtonText: '확인',
            heightAuto: false
        }

        Swal.fire(defaultOption);
    },
    confirm : (title, func) => {
        const defaultOption = {
            icon : 'warning',
            title : title,
            text : '',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '확인',
            cancelButtonText: '취소',
        }
        Swal.fire(defaultOption)
            .then(result => {
                if(result.isConfirmed) {
                    func();
                }
            });
    }
}
