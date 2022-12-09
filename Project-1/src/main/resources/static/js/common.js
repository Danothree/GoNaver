const common = {
    nullCheck : (data,...list) => {

        for (let i = 0; i < list.length; i++) {
            if(data[list[i]] == undefined || data[list[i]] == "") {
                common.showAlert('정보를 다 입력해주세요','warning');
                return true;
            }
        }
        return false;
    },
    showAlert : (title, option, url) => {
        let i = option ? option : 'success';
        const defaultOption = {
            title : title,
            text : '',
            icon : i,
            confirmButtonText: '확인',
            heightAuto: false
        }

        swal.fire(defaultOption)
            .then(result => {
                if(result.isConfirmed && url != undefined) {
                    document.location.href = url;
                }
            })
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
        swal.fire(defaultOption)
            .then(result => {
                if(result.isConfirmed) {
                    func();
                }
            });
    },
    serialize : (rawData) => {
        let result = {};

        for(let [key, val] of rawData) {
            let sel = document.querySelectorAll("[name=" + key + "]");

            if (sel.length > 1) {
                if (result[key] === undefined) {
                    result[key] = [];
                }
                result[key].push(val);
            }
            else {
                result[key] = val;
            }
        }
        return result;
    }
}
