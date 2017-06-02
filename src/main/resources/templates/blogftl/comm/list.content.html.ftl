
<#list blogPage.list as row>
<div class="row">
    <#list row as blogMeta>
    <div class="blogmetaList col-md-6">
        <ul class="list-group ">
            <li class="list-group-item list-group-item-success"><a href="#">${blogMeta.title}</a></li>
            <li class="list-group-item list-group-item-info justify-content-between">
                <small>by ${blogMeta.author}</small>
                <small>${blogMeta.dtStr}</small>
            </li>
        </ul>
    </div>
    </#list>
</div>
</#list>



<div class="row ">
    <div class="blogmetaList col-md-12 justify-content-between">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-between">
                <li class="page-item">
                    <a class="page-link" href="bloglist.html?page=${blogPage.last}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">Previous</span>
                    </a>
                </li>
                <li class="page-item">
                    <form id="jumpBlogPageForm" class="form-inline">
                        <input  type="number" style="width: 80px" class="form-control" id="jumpBlogPageInput" value="${blogPage.page}" placeholder="${blogPage.page}">
                        /${blogPage.pageCount} &nbsp;
                        <button type="submit" class="btn btn-primary ">Go</button>
                    </form>
                </li>
                <li class="page-item">
                    <a class="page-link" href="bloglist.html?page=${blogPage.next}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                        <span class="sr-only">Next</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>

</div> <!-- pagination row -->