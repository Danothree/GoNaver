customFetch = {

    //동기화 GET
    asyncGet : async (host, path) => {
        let url = `${host}`;
        if(path !== '') {
            url = `${host}/${path}`;
        }
        const res = await fetch(url);
        const data = res.json();
        if(res.ok) {
            return data;
        } else {
            common.showAlert("통신 실패.",'error');
        }
    },

    //GET
    get : (host, path) => {
        let url = `${host}`;
        if(path !== '') {
            url = `${host}/${path}`;
        }
        const res = fetch(url);
        if(res.ok) {
            common.showAlert("완료!");
        } else {
            common.showAlert("통신 실패.",'error');
        }
    },

    //동기화 POST
    asyncPost : async (host, path, body, headers = {}) => {
        let url = `${host}`;
        if(path !== '') {
            url = `${host}/${path}`;
        }
        const options = {
            method : 'POST',
            headers : {
                'Content-Type' : 'application/json',
                ...headers,
            },
            body : body,

        };
        const res = await fetch(url, options);
        if(res.ok) {
            common.showAlert("완료!");
        } else {
            common.showAlert('통신 장애','error');
        }
    },

    //POST
    post : (host, path, body, headers = {}) => {
        let url = `${host}`;
        if(path !== '') {
            url = `${host}/${path}`;
        }
        const options = {
            method : 'POST',
            headers : {
                'Content-Type' : 'application/json',
                ...headers,
            },
            body : body,

        };
        const res = fetch(url, options);
        if(res.ok) {
            common.showAlert("완료!");
        } else {
            common.showAlert('통신 장애','error');
        }
    },

    //동기화 PATCH
    asyncPatch : async (host, path, body, headers = {}) => {
        let url = `${host}`;
        if(path !== '') {
            url = `${host}/${path}`;
        }
        const options = {
            method : 'PATCH',
            headers : {
                'ContentType' : 'application/json',
                ...headers,
            },
            body : body,
        };
        const res = await fetch(url, options);
        if(res.ok) {
            common.showAlert("완료!");
        } else {
            common.showAlert('수정 실패','error');
        }
    },

    //PATCH
    patch : (host, path, body, headers = {}) => {
        let url = `${host}`;
        if(path !== '') {
            url = `${host}/${path}`;
        }
        const options = {
            method : 'PATCH',
            headers : {
                'ContentType' : 'application/json',
                ...headers,
            },
            body : body,
        };
        const res = fetch(url, options);
        if(res.ok) {
            common.showAlert("완료!");
        } else {
            common.showAlert('수정 실패','error');
        }
    },

    //동기화 DELETE
    asyncDelete : async (host, path, body, headers = {}) => {
        let url = `${host}`;
        if(path !== '') {
            url = `${host}/${path}`;
        }
        const options = {
            method : 'DELETE',
            headers : {
                'Content-Type' : 'application/json',
                ...headers,
            },
            body : JSON.stringify(body),
        };
        const res = await fetch(url, options);
        if(res.ok) {
            common.showAlert("완료!");
        } else {
            common.showAlert('삭제 실패','error');
        }
    },

    //DELETE
    delete : (host, path, body, headers = {}) => {
        let url = `${host}`;
        if(path !== '') {
            url = `${host}/${path}`;
        }
        const options = {
            method : 'DELETE',
            headers : {
                'Content-Type' : 'application/json',
                ...headers,
            },
            body : JSON.stringify(body),
        };
        const res = fetch(url, options);
        if(res.ok) {
            common.showAlert("완료!");
        } else {
            common.showAlert('삭제 실패','error');
        }
    },
}
