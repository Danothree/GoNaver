const common = {
    nullCheck : (data,...list) => {
        let result = false;
        for (let i = 0; i < list.length; i++) {
            if(data[list[i]] == undefined) {
                result = true;
            }
        }
        return result;
    },
    showAlert : function(title, option) {
        const defaultOption = {
            title : title,
            text : '',
            icon : 'info',
            confirmButtonText: '확인',
            heightAuto: false
        }

        Swal.fire(defaultOption);
    },
    // confirm : function(title, option, fnProc, cancleProc) {
    //     var defaultOption = {
    //         icon : 'warning',
    //         title : title,
    //         text : '',
    //         showCancelButton: true,
    //         confirmButtonColor: '#3085d6',
    //         cancelButtonColor: '#d33',
    //         confirmButtonText: '확인',
    //         cancelButtonText: '취소',
    //         heightAuto: false
    //     }
    //     var newObj = $.extend(defaultOption, typeof(option)=="undefined" ? {} : option);
    //     swal.fire(newObj).then((result) => {
    //         if(result.value) {
    //             fnProc();
    //         } else {
    //             cancleProc();
    //         }
    //     });
    // }
}
