import express from 'express';
import cors from 'cors';
import colors from 'colors';
import notesRoutes from '../routes/note_routes.js';

const PORT = process.env.PORT || 3005;

export const config_express_app = async () => {
  const app = express();
  app.use(express.urlencoded({ extended: false }));
  app.use(express.json());
  app.use(cors({}));

  app.use('/api', notesRoutes);

  listen_on_port(app);
};

const listen_on_port = (app) => {
  app.listen(PORT, () => console.log(`Server started via port ${PORT}`.blue.bold));
};

export default config_express_app;
