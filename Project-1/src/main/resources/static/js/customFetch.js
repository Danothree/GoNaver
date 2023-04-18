class CustomFetch {

    constructor(host, path) {
        this.host = host;
        this.path = path;
    }

    //GET
    async get() {
        let url = this.host;
        if(this.path !== '') {
            url = `${this.host}/${this.path}`;
        }
        const res = await fetch(url);
        if(res.ok) {
            new Common().showAlert("완료!");
            return await res.json();
        } else {
            new Common().showAlert("통신 실패.",'error');
        }
    }

    //POST
    async post(body, headers = {}) {
        let url = this.host;
        if(this.path !== '') {
            url = `${this.host}/${this.path}`;
        }
        const options = {
            method : 'POST',
            headers : {
                'Content-Type' : 'application/json',
                ...headers,
            },
            body : JSON.stringify(body),

        };
        const res = await fetch(url, options);
        if(res.ok) {
            new Common().showAlert("완료!");
            return await res.json();
        } else {
            new Common().showAlert('통신 장애','error');
        }
    }

    //PATCH
    async patch(body, headers = {}) {
        let url = this.host;
        if(this.path !== '') {
            url = `${this.host}/${this.path}`;
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
            new Common().showAlert("완료!");
            return res.json();
        } else {
            new Common().showAlert('수정 실패','error');
        }
    }

    //DELETE
    async delete(body, headers = {}) {
        let url = this.host;
        if(this.path !== '') {
            url = `${this.host}/${this.path}`;
        }
        const options = {
            method : 'DELETE',
            headers : {
                'Content-Type' : 'application/json',
                ...headers,
            },
            body : body,
        };
        const res = await fetch(url, options);
        if(res.ok) {
            new Common().showAlert("완료!");
            return res.json();
        } else {
            new Common().showAlert('삭제 실패','error');
        }
    }
}
