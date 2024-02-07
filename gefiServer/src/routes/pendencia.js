const Router = require('express');
const pendenciasController = require('../controllers/pendencia');
const router = Router()

const rota = `/pendencias`;
router.post(rota, pendenciasController.adiciona);
router.get(rota, pendenciasController.lista);
router.get(`${rota}/:id`, pendenciasController.buscaPorId);
router.get(`${rota}/controle/:controle_id`, pendenciasController.buscaUltimaPorControleId);
router.patch(`${rota}/:id`, pendenciasController.atualiza);
router.delete(`${rota}/:id`, pendenciasController.deleta);
module.exports = router;


