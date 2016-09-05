var attributes; // global attributes list

+function($) {
    // MODAL DATA-API
    // ==============
    $(document).on(
            'click.bs.modal.data-api',
            '[data-toggle="modal"]',
            function(e) {
                var $this = $(this)
                var href = $this.attr('href')
                var $target = $($this.attr('data-target')
                        || (href && href.replace(/.*(?=#[^\s]+$)/, ''))) // strip
                                                                            // for
                                                                            // ie7
                var option = $target.data('bs.modal') ? 'toggle' : $.extend({
                    remote : !/#/.test(href) && href
                }, $target.data(), $this.data())

                $("#param1").attr("value", $this.attr("value"));
                $("#param2").attr("value", $this.attr("value"));
                $("#param3").attr("value", $this.attr("value"));
                $("#param4").attr("value", $this.attr("value"));
              $("#param11").attr("value", $this.attr("value2"));
              $("#param12").attr("value", $this.attr("value3"));
                if ($this.is('a'))
                    e.preventDefault()
                $target.one('show.bs.modal', function(showEvent) {
                    if (showEvent.isDefaultPrevented())
                        return // only register focus restorer if modal will
                                // actually get shown
                        $target.one('hidden.bs.modal', function() {
                            $this.is(':visible') && $this.trigger('focus')
                        })
                })
                Plugin.call($target, option, this)
            })

}(jQuery);

function editModal(e) {
    e.preventDefault();
    var el = $(this);
    attributes = this.attributes;
    for (var i = 0; i < attributes.length; i++) {
        if (attributes[i].name.indexOf('param') != -1) {
            var element = $('#' + attributes[i].name.substr(6));

            // inputs
            if (element.is('input')) {

                // text type
                if (element.attr('type') == 'text') {
                    element.val(attributes[i].value);
                } else
                // checkbox type
                if (element.attr('type') == 'checkbox') {
                    if (attributes[i].value == 'true') {
                        element.prop("checked", true);
                    } else {
                        element.prop("checked", false);
                    }
                } else
                // hidden
                if (element.attr('type') == 'hidden') {
                    element.attr("value", attributes[i].value);
                } else
                //date
                if (element.attr('type') == 'date') {
                    element.val(attributes[i].value);
                }else
                //number
                if (element.attr('type') == 'number') {
                    element.val(attributes[i].value);
                }
            } else
            // select
            if (element.is('select')) {
                element.val(attributes[i].value).prop('selected', true);
            } else
            // textarea
            if (element.is('textarea')) {
                element.val(attributes[i].value);
            } else
            // label
            if (element.is('label')) {
                element.text(attributes[i].value);
            } else
            // p
            if (element.is('p')) {
                element.text(attributes[i].value);
            } else
            // div
            if (element.is('div')) {
                element.html(attributes[i].value);
            }
        }
    }
}

function cleanModal(e) {
    e.preventDefault();
    if (attributes != null)
        for (var i = 0; i < attributes.length; i++) {
            if (attributes[i].name.indexOf('param') != -1) {
                var element = $('#' + attributes[i].name.substr(6));

                // inputs
                if (element.is('input')) {

                    // text type
                    if (element.attr('type') == 'text') {
                        element.val("");
                    } else
                    // checkbox type
                    if (element.attr('type') == 'checkbox') {
                        element.prop("checked", false);
                    } else
                    // hidden
                    if (element.attr('type') == 'hidden') {
                        element.attr("value", "");
                    } else
                    //date
                    if (element.attr('type') == 'date') {
                        element.val("");
                    } else
                    //number
                    if (element.attr('type') == 'number') {
                        element.val("0");
                    }
                } else
                // select
                if (element.is('select')) {
                    element.val("").prop('selected', true);
                } else
                // textarea
                if (element.is('textarea')) {
                    element.val("");
                } else
                 // label
                if (element.is('label')) {
                    element.val("");
                } else
                if (element.is('div')) {
                    element.html("");
                }
                element.show();
            }
        }
}

function setVisible(e) {
    e.preventDefault();
    var el = $(this);
    attributes = this.attributes;
    for (var i = 0; i < attributes.length; i++) {
        if (attributes[i].name.indexOf('visible') != -1) {
            var element = $('#' + attributes[i].name.substr(8));
            if (attributes[i].value == 'false') {
                element.hide();
            } else {
                element.show();
            }
        }
    }
}