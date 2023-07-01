import { config_express_app } from './config/configRoutes.js';
import { createDBConnection as dbConnection } from './middleware/notes.js';

const main = async () => {
  config_express_app();
  await dbConnection();
};

main();
