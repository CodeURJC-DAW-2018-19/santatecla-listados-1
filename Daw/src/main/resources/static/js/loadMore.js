var nextPage = 1;
function loadMoreLesson() {
    var urlPage = "/loadMore?page=" + nextPage;
    $.ajax({
        url: urlPage
    }).done(function (data) {
        $("#showMore").append(data);
        nextPage++;
    })
}