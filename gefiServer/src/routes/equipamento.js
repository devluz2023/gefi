const Router = require('express');
const equipamentoController = require('../controllers/equipamento');
const router = Router()

const rota = `/equipamentos`;
router.post(rota, equipamentoController.adiciona);
router.patch(`${rota}/:id`, equipamentoController.atualiza);
router.get(rota, equipamentoController.lista);
router.get(`${rota}/nao-associados`, equipamentoController.listaNaoAssociados);
router.get(`${rota}/:id`, equipamentoController.buscaPorId);
router.delete(`${rota}/:id`, equipamentoController.deleta);

module.exports = router;


