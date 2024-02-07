const bodyParser = require('body-parser')
const areaRoute = require('./area');
const controleRoute = require('./controle');
const equipamentoRoute = require('./equipamento');
const solicitacaoRoute = require('./solicitacao');
const usuarioRoute = require('./usuario');
const pendenciaRoute = require('./pendencia');
const homeaRoute = require('./home');
module.exports = app => {
 app.use(
   bodyParser.json(),
   bodyParser.urlencoded({ extended: true }),
   areaRoute,
   controleRoute,
   equipamentoRoute,
   solicitacaoRoute,
   usuarioRoute,
   pendenciaRoute,
   homeaRoute
   )
 }
