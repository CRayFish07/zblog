
<div  id="modalLogin" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">${cfg.strLogin}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <div class="form-group row">
                    <label for="inputLoginPassword" class=" col-sm-2 col-form-label">${cfg.strPassword}</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="inputLoginPassword" placeholder="">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">${cfg.strClose}</button>
                <input type="button" id="buttonLoginSubmit" class="btn btn-primary" value="${cfg.strLogin}"></input>
            </div>
        </div>
    </div>
</div>  <!-- deleteModal -->