import { useState } from "react";
import Consultas from "./pages/Consultas";
import Pacientes from "./pages/Pacientes";
import Medicos from "./pages/Medicos";

export default function App() {
    const [pagina, setPagina] = useState("consultas");

    return (
        <div style={{ padding: 20 }}>
            <h1>SALUS</h1>

            <button onClick={() => setPagina("consultas")}>
                Consultas
            </button>

            <button onClick={() => setPagina("pacientes")}>
                Pacientes
            </button>

            <button onClick={() => setPagina("medicos")}>
                Médicos
            </button>

            <hr />

            {pagina === "consultas" && <Consultas />}
            {pagina === "pacientes" && <Pacientes />}
            {pagina === "medicos" && <Medicos />}
        </div>
    );
}
