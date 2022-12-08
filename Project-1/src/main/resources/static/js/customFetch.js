customFetch = {

    //동기화 GET
    asyncGet : async (host, path) => {
        const url = `${host}/${path}`;
        const res = await fetch(url);
        const data = res.json();
        if(res.ok) {
            return data;
        } else {
            common.showAlert("통신 실패.");
        }
    },

    //GET
    get : (host, path) => {
        const url = `${host}/${path}`;
        const res = fetch(url);
        const data = res.json();
        if(res.ok) {
            return data;
        } else {
            common.showAlert("통신 실패.");
        }
    },

    //동기화 POST
    asyncPost : async (host, path, body, headers = {}) => {
        const url = `${host}/${path}`;
        const options = {
            method : 'POST',
            headers : {
                'ContentType' : 'application/json',
                ...headers,
            },
            body :JSON.stringify(body),

        };
        const res = await fetch(url, options);
        const data = res.json();
        if(res.ok) {
            return data;
        } else {
            common.showAlert('통신 장애');
        }
    },

    //POST
    post : (host, path, body, headers = {}) => {
        const url = `${host}/${path}`;
        const options = {
            method : 'POST',
            headers : {
                'ContentType' : 'application/json',
                ...headers,
            },
            body :JSON.stringify(body),

        };
        const res = fetch(url, options);
        const data = res.json();
        if(res.ok) {
            return data;
        } else {
            common.showAlert('통신 장애');
        }
    },

    //동기화 PATCH
    asyncPatch : async (host, path, body, headers = {}) => {
        const url = `${host}/${path}`;
        const options = {
            method : 'PATCH',
            headers : {
                'ContentType' : 'application/json',
                ...headers,
            },
            body : JSON.stringify(body),
        };
        const res = await fetch(url, options);
        const data = res.json();
        if(res.ok) {
            return data;
        } else {
            common.showAlert('수정 실패');
        }
    },

    //PATCH
    patch : (host, path, body, headers = {}) => {
        const url = `${host}/${path}`;
        const options = {
            method : 'PATCH',
            headers : {
                'ContentType' : 'application/json',
                ...headers,
            },
            body : JSON.stringify(body),
        };
        const res = fetch(url, options);
        const data = res.json();
        if(res.ok) {
            return data;
        } else {
            common.showAlert('수정 실패');
        }
    },

    //동기화 DELETE
    asyncDelete : async (host, path, body, headers = {}) => {
        const url = `http://${host}/${path}`;
        const options = {
            method : 'DELETE',
            headers : {
                'Content-Type' : 'application/json',
                ...headers,
            },
            body : JSON.stringify(body),
        };
        const res = await fetch(url, options);
        const data = res.json();
        if(res.ok) {
            return data;
        } else {
            common.showAlert('삭제 실패');
        }
    },

    //DELETE
    delete : (host, path, body, headers = {}) => {
        const url = `http://${host}/${path}`;
        const options = {
            method : 'DELETE',
            headers : {
                'Content-Type' : 'application/json',
                ...headers,
            },
            body : JSON.stringify(body),
        };
        const res = fetch(url, options);
        const data = res.json();
        if(res.ok) {
            return data;
        } else {
            common.showAlert('삭제 실패');
        }
    },
}
