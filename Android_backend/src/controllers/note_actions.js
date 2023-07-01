import mongoose from 'mongoose';
import { ODM } from '../middleware/notes.js';

export const addNote = async (req) => {
  try {
    const note = await ODM.models.Note({
      _id: new mongoose.Types.ObjectId(),
      title: req.title,
      text: req.text,
    });
    await note.save();
    console.log('Note created: ' + note + '\n');
    return { success: true, message: 'Note created successfully' };
  } catch (e) {
    console.log('ERROR CREATING NOTE ' + e.message);
    return { success: false, message: 'SOMETHING WENT WRONG !' };
  }
};

export const getNotes = async (req) => {
  let filter = {};
  if (req) {
    if (req.title !== undefined) filter.title = req.title;
  }
  try {
    const result = await ODM.models.Note.find(filter);
    return result;
  } catch (e) {
    console.log('e', e);
    return [];
  }
};

export const removeNote = async (req) => {
  try {
    const result = await ODM.models.Note.findOneAndRemove({ _id: req._id });
    return result
      ? { success: true, message: 'Note deleted' }
      : { success: false, message: 'Note not deleted' };
  } catch (e) {
    console.log('ERROR DELETING NOTE', e);
    return { success: false, message: 'Something went wrong' };
  }
};

export const updateNote = async (req) => {
  const filter = { _id: req._id };
  const update = { name: req.title };
  const option = { new: true };
  const result = await ODM.models.Note.findOneAndUpdate(filter, update, option);

  return { success: true, message: 'Note updated' };
};
