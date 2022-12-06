customFetch = {

}

async function get(host, path) {
    const url = `http://${host}/${path}`;
    await fetch(url);
}

async function post(host, path, body, headers= {}) {
    const url = `http://${host}/${path}`;
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
        alert('통신 장애');
    }
}

async function patch(host, path, body, headers = {}) {
    const url = `http://${host}/${path}`;
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
        alert('수정 완료');
    } else {
        alert('수정 실패');
    }
}

async function del(host, path, body, headers = {}) {
    const url = `http://${host}/${path}`;
    const options = {
        method : 'DELETE',
        headers : {
            'Content-Type' : 'application/json',
            ...headers,
        },
        body : JSON.stringify(body),
    };
    const res = await  fetch(url, options);
    const data = res.json();
    if(res.ok) {
        alert('삭제되었습니다');
    } else {
        alert('삭제 실패');
    }
}