;(function() {
    angular
        .module('app')
        .service('PedidoService', ['$http', function($http) {
            return {
                get: function() {
                    return $http.get('/api/v1/pedidos');
                },
                save: function(data) {
                    return $http.post('/api/v1/pedidos', data);
                }
            };
        }]);
})();
