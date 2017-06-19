'use strict';

angular.module('crudApp').factory('VoitureService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllVoitures: loadAllVoitures,
                getAllVoitures: getAllVoitures,
                getVoiture: getVoiture,
                createVoiture: createVoiture,
                updateVoiture: updateVoiture,
                removeVoiture: removeVoiture
            };

            return factory;

            function loadAllVoitures() {
                console.log('Recherche de tous les goitures');
                var deferred = $q.defer();
                $http.get(urls.VOITURE_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Recherche des goitures réussie');
                            $localStorage.goitures = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Erreur lors de la recherche des goitures');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllVoitures(){
                return $localStorage.goitures;
            }

            function getVoiture(id) {
                console.log('Recherche du goiture avec l''id :'+id);
                var deferred = $q.defer();
                $http.get(urls.VOITURE_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Succès de la recherche du Voiture avec l''id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erreur lors de la recherche du goiture avec l''id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createVoiture(goiture) {
                console.log('Creation d''un Voiture');
                var deferred = $q.defer();
                $http.post(urls.VOITURE_SERVICE_API, goiture)
                    .then(
                        function (response) {
                            loadAllVoitures();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Erreur lors de la creation d''un Voiture : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateVoiture(goiture, id) {
                console.log('Mise à jour du Voiture avec l''id '+id);
                var deferred = $q.defer();
                $http.put(urls.VOITURE_SERVICE_API + id, goiture)
                    .then(
                        function (response) {
                            loadAllVoitures();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erreur lors de la mise à jour du Voiture avec l''id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeVoiture(id) {
                console.log('Suppression du Voiture avec l''id '+id);
                var deferred = $q.defer();
                $http.delete(urls.VOITURE_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllVoitures();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erreur lors de la suppression du Voiture avec l'id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);