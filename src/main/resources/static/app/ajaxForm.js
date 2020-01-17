var ajaxForm = {
    type : 'POST',
    url : '',
    contentType : 'application/json; charset=utf-8', // contentType is the type of data you're sending
    data : null,
    dataType : 'json', //dataType is what you're expecting back from the server
    build : function(url, data){
        this.url=url;
        this.data=data;
    },
    doAjax: function(){
        $.ajax({
            type: this.type,
            url: this.url,
            contentType: this.contentType,
            data: this.data,
            dataType: this.dataType,
            error: function(request, status, error){
                console.log(error);
            },
            success: function(response, status, request){
                console.log(request);
                console.log(response);
            },
        })
    }
};
