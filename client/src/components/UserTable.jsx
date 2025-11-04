import React from "react";
import { deleteUser } from "../modules/user/UserService";

export default function UserTable({ users, onEdit }) {
  const handleDelete = async (id) => {
    if (window.confirm("¿Eliminar usuario?")) {
      await deleteUser(id);
      window.location.reload(); 
    }
  };

  return (
    <div className="overflow-x-auto mt-4">
      <table className="min-w-full bg-white border border-gray-200">
        <thead className="bg-gray-100">
          <tr>
            <th className="py-2 px-4 border-b">Nombre</th>
            <th className="py-2 px-4 border-b">Apellido</th>
            <th className="py-2 px-4 border-b">Email</th>
            <th className="py-2 px-4 border-b">Teléfono</th>
            <th className="py-2 px-4 border-b">Acciones</th>
          </tr>
        </thead>
        <tbody>
          {Array.isArray(users) &&
            users.map((user) => (
              <tr key={user.id} className="text-center">
                <td className="py-2 px-4 border-b">{user.name}</td>
                <td className="py-2 px-4 border-b">{user.lastName}</td>
                <td className="py-2 px-4 border-b">{user.email}</td>
                <td className="py-2 px-4 border-b">{user.phoneNumber}</td>
                <td className="py-2 px-4 border-b">
                  <button
                    className="bg-yellow-400 text-white px-2 py-1 rounded mr-2"
                    onClick={() => onEdit(user)}
                  >
                    Editar
                  </button>
                  <button
                    className="bg-red-500 text-white px-2 py-1 rounded"
                    onClick={() => handleDelete(user.id)}
                  >
                    Eliminar
                  </button>
                </td>
              </tr>
            ))}
        </tbody>
      </table>
    </div>
  );
}
