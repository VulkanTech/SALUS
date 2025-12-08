import Medicos from "./pages/Medicos";
import Pacientes from "./pages/Pacientes";
import Consultas from "./pages/Consultas";
import Relatorios from "./pages/Relatorios";

function App() {
    return (
        <div style={{ padding: 20 }}>
            <h1>SALUS</h1>

            <Medicos />
            <Pacientes />
            <Consultas />
            <Relatorios />
        </div>
    );
}

export default App;
