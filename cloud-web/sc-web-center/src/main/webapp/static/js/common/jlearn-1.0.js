(function () {
    // jlearn 命名空间
    window['j'] = {};
    /**
     *移除空白字符
     * @param other
     */
    if (!String.trim) {
        String.prototype.trim = function () {
            return this.replace(/^\s+|\s+$/g, '');
        }
    }

    //确定浏览器是否与整个库兼容
    function isCompatible(other) {
        //使用能力检测来检查必要条件
        if (other === false
            || !Array.prototype.push
            || !Object.hasOwnProperty
            || !document.createElement
            || !document.getElementsByTagName) {
            return false;
        }
        return true;
    }


    window['j']['isCompatible'] = isCompatible;


    //通过id获取元素
    // 传入一个id 返回相应元素
    //传入多个id 则返回元素数组
    function $() {
        var elements = new Array();
        //查找作为参数提供的所有元素
        for (var i = 0; i < arguments.length; i++) {
            var element = arguments[i];
            //如果参数是一个字符串那假设它是一个id
            if (typeof element == 'string') {
                element = document.getElementById(element);
            }
            //如果只提供一个参数
            //则立即返回这个元素
            if (arguments.length == 1) {
                return element;
            }
            //否则 添加到数组中
            elements.push(element);
        }
        return elements;
    }


    window['j']['$'] = $;

    /**
     * 为元素添加事件监听
     * @param node  要添加事件监听的元素
     * @param type 事件类型
     * @param listener 监听函数 可以是已定义函数 也可以是匿名函数
     */
    function addEvent(node, type, listener) {
        //检查是否兼容
        if (!isCompatible()) {
            return false;
        }
        //保证node 可以是对象 也可以是对象的id
        if (!(node = $(node))) {
            return false;
        }
        if (node.addEventListener) {
            //W3C的方法
            node.addEventListener(type, listener, false);
            return true;
        } else if (node.attachEvent) {
            //MSIE的方法
            node['e' + type + listener] = listener;
            node[type + listener] = function () {
                node['e' + type + listener](window.event);
            }
            node.attachEvent("on" + type, node[type + listener]);
            return true;
        }
        //如果两种方法都不合适
        return false;
    }


    window['j']['addEvent'] = addEvent;

    /**
     * 移除事件监听
     * @param node 要移除事件监听的元素
     * @param type 事件类型
     * @param listener  监听函数
     */
    function removeEvent(node, type, listener) {
        //保证node 可以是对象 也可以是对象的id
        if (!(node = $(node))) {
            return false;
        }
        if (node.removeEventListener) {
            //W3C方法
            node.removeEventListener(type, listener, false);
            return true;
        } else if (node.detachEvent) {
            //MSIE方法
            node.detachEvent('on' + type, node[type + listener]);
            node[type + listener] = null;
            return true;
        }
        return false;
    }


    window['j']['removeEvent'] = removeEvent;

    /**
     * 通过className 获取元素
     * @param className
     * @param tag   要获取的标签类型  如果为*,则表示获取有的标签
     * @param parent  父元素  如果未指定该参数 则默认为 document
     */
    function getElementsByClassName(className, tag, parent) {
        parent = parent || document;
        if (!(parent = $(parent))) {
            return false;
        }
        //查找所以的标签
        var allTags = (tag == "*" && parent.all) ? parent.all : parent.getElementsByTagName(tag);
        var matchElements = new Array();
        //创建一个正则表达式，保证className 正确
        className = className.replace(/\-/g, "\\-");
        var regex = new RegExp("(^|\\s)" + className + "(\\s|$)");
        var element;
        //检查每一个元素
        for (var i = 0; i < allTags.length; i++) {
            element = allTags[i];
            if (regex.test(element.className)) {
                matchElements.push(element);
            }
        }
        return matchElements;
    }


    window['j']['getElementsByClassName'] = getElementsByClassName;

    /**
     * 切换显示样式
     * @param node 要应用的元素
     * @param value  样式值
     */
    function toggleDisplay(node, value) {
        if (!(node = $(node))) {
            return false;
        }
        if (node.style.display != "none") {
            node.style.display = "none";
        } else {
            node.style.display = value || "";
        }
        return true;
    }

    window['j']['toggleDisplay'] = toggleDisplay;

    /**
     * 某节点之后插入新节点
     * @param node  新的节点
     * @param referenceNode 某节点
     */
    function insertAfter(node, referenceNode) {
        if (!(node = $(node))) {
            return false;
        }
        if (!(referenceNode = $(referenceNode))) {
            return false;
        }
        return referenceNode.parentNode.insertBefore(node, referenceNode.nextSibling);
    }

    window['j']['insertAfter'] = insertAfter;

    /**
     * 移除孩子节点
     * @param parent 父节点
     */
    function removeChildren(parent) {
        if (!(parent = $(parent))) {
            return false;
        }
        //当存在子节点时 删除该节子点
        while (parent.firstChild) {
            parent.firstChild.parentNode.removeChild(parent.firstChild);
        }
        //返回父元素 以便实现连缀
        return parent;
    }

    window['j']['removeChildren'] = removeChildren;

    /**
     * 添加前孩子
     * @param parent 父节点
     * @param newChild 新孩子
     */
    function prependChild(parent, newChild) {
        if (!(parent = $(parent))) {
            return false;
        }
        if (!(newChild = $(newChild))) {
            return false;
        }
        if (parent.firstChild) {
            //如果存在一个子节点，则在这个子节点之前插入
            parent.insertBefore(newChild, parent.firstChild);
        } else {
            //如果没有子节点则直接添加
            parent.appendChild(newChild);
        }
        return parent;
    }

    window['j']['prependChild'] = prependChild;


    /**
     * url 绑定参数键值对
     * @param sURL  给定的url
     * @param sParamName  键
     * @param sParamValue 值
     */
    function addURLParam(sURL, sParamName, sParamValue) {
        sURL += (sURL.indexOf("?") == -1 ? "?" : "&");
        sURL += encodeURIComponent(sParamName) + "=" + encodeURIComponent(sParamValue);
        //    sURL += sParamName + "=" + sParamValue;
        return sURL;
    }

    window['j']['addURLParam'] = addURLParam;
    /**
     * url 绑定参数键值对
     * @param sURL  给定的url
     * @param sParamName  键
     * @param sParamValue 值
     */
    function addURLParam2(sURL, sParamName, sParamValue) {
        sURL += (sURL.indexOf("?") == -1 ? "?" : "&");
        sURL += (sParamName) + "=" + (sParamValue);
        //    sURL += sParamName + "=" + sParamValue;
        return sURL;
    }
    window['j']['addURLParam2'] = addURLParam2;

    /*
     parseJSON(string,filter)
     A slightly modified version of the public domain method
     at http://www.json.org/json.js This method parses a JSON text
     to produce an object or array. It can throw a
     SyntaxError exception.

     The optional filter parameter is a function which can filter and
     transform the results. It receives each of the keys and values, and
     its return value is used instead of the original value. If it
     returns what it received, then structure is not modified. If it
     returns undefined then the member is deleted.

     Example:

     // Parse the text. If a key contains the string 'date' then
     // convert the value to a date.

     myData = parseJSON(string,function (key, value) {
     return key.indexOf('date') >= 0 ? new Date(value) : value;
     });

     */
    function parseJSON(s, filter) {
        var j;

        function walk(k, v) {
            var i;
            if (v && typeof v === 'object') {
                for (i in v) {
                    if (v.hasOwnProperty(i)) {
                        v[i] = walk(i, v[i]);
                    }
                }
            }
            return filter(k, v);
        }


        // Parsing happens in three stages. In the first stage, we run the
        // text against a regular expression which looks for non-JSON
        // characters. We are especially concerned with '()' and 'new'
        // because they can cause invocation, and '=' because it can cause
        // mutation. But just to be safe, we will reject all unexpected
        // characters.

        if (/^("(\\.|[^"\\\n\r])*?"|[,:{}\[\]0-9.\-+Eaeflnr-u \n\r\t])+?$/.
            test(s)) {

            // In the second stage we use the eval function to compile the text
            // into a JavaScript structure. The '{' operator is subject to a
            // syntactic ambiguity in JavaScript: it can begin a block or an
            // object literal. We wrap the text in parens to eliminate
            // the ambiguity.

            try {
                j = eval('(' + s + ')');
            } catch (e) {
                throw new SyntaxError("parseJSON");
            }
        } else {
            throw new SyntaxError("parseJSON");
        }

        // In the optional third stage, we recursively walk the new structure,
        // passing each name/value pair to a filter function for possible
        // transformation.

        if (typeof filter === 'function') {
            j = walk('', j);
        }
        return j;
    }


    /**
     * 创建一个XMLHttpRequest 对象
     */
    function getRequestObject(url, options) {

        var req = false;
        if (window.XMLHttpRequest) {
            var req = new window.XMLHttpRequest();
        } else if (window.ActiveXObject) {
            var req = new window.ActiveXObject('Microsoft.XMLHTTP');
        }
        if (!req) return false;

        // 定义默认的可选参数
        options = options || {};
        options.method = options.method || 'GET';
        options.send = options.send || null;

        // 定义每个状态的监听器
        req.onreadystatechange = function () {
            switch (req.readyState) {
                case 1:
                    // 载入 loadListener 监听器
                    if (options.loadListener) {
                        options.loadListener.apply(req, arguments);
                    }
                    break;
                case 2:
                    // 载入成功  loadedListener  监听器
                    if (options.loadedListener) {
                        options.loadedListener.apply(req, arguments);
                    }
                    break;
                case 3:
                    // 交互   ineractive
                    if (options.ineractiveListener) {
                        options.ineractiveListener.apply(req, arguments);
                    }
                    break;
                case 4:
                    // 完成  complete
                    try {
                        if (req.status && req.status == 200) {

                            //指定特定content-type的监听器
                            //content-type 有可能包含字符声明：
                            //Content-Type:text/html;charset=ISO-8859-4
                            //所以需要匹配出需要的部分
                            var contentType = req.getResponseHeader('Content-Type');
                            var mimeType = contentType.match(/\s*([^;]+)\s*(;|$)/i)[1];

                            switch (mimeType) {
                                case 'text/javascript':
                                case 'application/javascript':

                                    //响应是js
                                    //所以使用req.responseText 作为回调函数的参数
                                    if (options.jsResponseListener) {
                                        options.jsResponseListener.call(req, req.responseText);
                                    }
                                    break;
                                case 'application/json':

                                    //因为响应是json 所以需要解析 ,使用前面定义的parseJSON函数 也可以使用eval('('+json+')')

                                    if (options.jsonResponseListener) {
                                        try {
                                            var json = parseJSON(req.responseText);
                                        } catch (e) {
                                            var json = false;
                                        }
                                        options.jsonResponseListener.call(req, json);
                                    }
                                    break;
                                case 'text/xml':
                                case 'application/xml':
                                case 'application/xhtml+xml':

                                    //响应是xml
                                    //所以使用req.responseXML作为回调函数的参数
                                    //是一个Documnet object 可以使用dom核心方法
                                    if (options.xmlResponseListener) {
                                        options.xmlResponseListener.call(req, req.responseXML);
                                    }
                                    break;
                                case 'text/html':

                                    //响应是HTML 所以使用req.responseText作为回调函数的参数
                                    if (options.htmlResponseListener) {
                                        options.htmlResponseListener.call(req, req.responseText);
                                    }
                                    break;
                            }

                            // 响应完成的监听器
                            if (options.completeListener) {
                                options.completeListener.apply(req, arguments);
                            }

                        } else {

                            //响应产生异常 错误处理监听器
                            if (options.errorListener) {
                                options.errorListener.apply(req, arguments);
                            }
                        }


                    } catch (e) {
                        //ignore errors
                        //alert('Response Error: ' + e);
                    }
                    break;
            }
        };
        // 打开请求
        req.open(options.method, url, true);

        // 添加一个特别的标签头
        req.setRequestHeader('X-Jlearn-Ajax-Request', 'AjaxRequest');

        return req;
    }

    window['j']['getRequestObject'] = getRequestObject;


    /**
     *
     * 发送  XMLHttpRequest 请求的包装函数
     * 使用方法：j.ajaxRequest(url,{
     *     method:'get',
     *     completeListener:function (){
     *         alert(this.responseText);
     *     }
     * });
     *
     */
    function ajaxRequest(url, options) {
        var req = getRequestObject(url, options);
        return req.send(options.send);
    }

    window['j']['ajaxRequest'] = ajaxRequest;


})()